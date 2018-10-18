# TaskManager
Tast Management Software
package com.taskmanager.controller;

import java.util.List;

import com.taskmanager.dal.EntityManagerBuilder;
import com.taskmanager.dal.TaskEntityManager;
import com.taskmanager.models.Task;

/**
 * The controller to handle project requests
 * 
 */
public class TaskController {

	private static TaskController instance = new TaskController();
	private final TaskEntityManager taskEntityManager;

	private TaskController() {
		this.taskEntityManager = EntityManagerBuilder.getEntityManager()
				.createEntityManager(TaskEntityManager.class);
	}

	/**
	 * 
	 * @return a singleton instance
	 */
	public static TaskController getInstance() {
		return instance;
	}

	/**
	 * Finds all projects for a user
	 * 
	 * @param ownerId
	 * @return
	 */
	public List<Task> findTasksForUser(long ownerId) {
		return this.taskEntityManager.findByOwner(ownerId);
	}

	/**
	 * Deletes a project
	 * 
	 * @param projectId
	 * @param ownerId
	 * @return the number of projects deleted
	 */
	public int deleteTaskById(final long projectId, long ownerId) {
		if (projectId == 0) {
			return 0;
		}
		return this.taskEntityManager.deleteByIdAndOwner(projectId, ownerId);
	}

	/**
	 * Saves a project into the db
	 * 
	 * @param project
	 *            {@link Task}
	 */
	public void saveTask(final Task project) {

		this.taskEntityManager.create(project);
	}

	/**
	 * Updates a project in the db
	 * 
	 * @param project
	 *            {@link Task}
	 */
	public void updateTask(final Task project) {

		this.taskEntityManager.edit(project);
	}
}
