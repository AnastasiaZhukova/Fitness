package com.github.anastasiazhukova.fitness.admin.screens.common.exercise.datasource

import com.github.anastasiazhukova.fitness.admin.screens.common.exercise.domain.ExerciseModel
import com.github.anastasiazhukova.fitness.authentication.user.IUserIdHolder
import com.github.anastasiazhukova.fitness.datasource.exercise.ExerciseDataModel
import com.github.anastasiazhukova.fitness.datasource.exercise.IExerciseDao
import com.github.anastasiazhukova.fitness.utils.IMapper
import com.github.anastasiazhukova.fitness.utils.extensions.generateId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseDataSource(
    private val userIdHolder: IUserIdHolder,
    private val exerciseDao: IExerciseDao,
    private val exerciseDataModelMapper: IMapper<ExerciseDataModel, ExerciseModel?>,
    private val exerciseModelDataMapper: IMapper<ExerciseModel, ExerciseDataModel>
) : IExerciseDataSource {

    override suspend fun getAll(): List<ExerciseModel> =
        withContext(Dispatchers.IO) {
            userIdHolder.getCurrentUserId()?.let { userId ->
                val dataModel = exerciseDao.getByAuthorId(userId)

                return@withContext dataModel.mapNotNull { exerciseDataModelMapper.invoke(it) }
            }

            emptyList<ExerciseModel>()
        }

    override suspend fun add(exerciseModel: ExerciseModel) {
        withContext(Dispatchers.IO) {
            userIdHolder.getCurrentUserId()?.let { userId ->
                val mappedModel = exerciseModelDataMapper.invoke(exerciseModel).copy(
                    id = generateId(),
                    authorId = userId
                )

                exerciseDao.add(mappedModel)
            }
        }
    }

    override suspend fun update(exerciseModel: ExerciseModel) {
        withContext(Dispatchers.IO) {
            userIdHolder.getCurrentUserId()?.let { userId ->
                if (userId == exerciseModel.authorId) {
                    val mappedModel = exerciseModelDataMapper.invoke(exerciseModel)
                    exerciseDao.update(mappedModel)
                }
            }
        }
    }

    override suspend fun delete(exerciseModel: ExerciseModel) {
        withContext(Dispatchers.IO) {
            userIdHolder.getCurrentUserId()?.let { userId ->
                if (userId == exerciseModel.authorId) {
                    exerciseDao.delete(exerciseModel.id)
                }
            }
        }
    }
}