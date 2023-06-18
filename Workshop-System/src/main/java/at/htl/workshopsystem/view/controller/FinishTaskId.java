package at.htl.workshopsystem.view.controller;

public final class FinishTaskId {
    private static FinishTaskId instance;

    private static Long taskId;

    private FinishTaskId(Long id) {
        this.taskId = id;
    }

    public static FinishTaskId getInstance(Long id) {
        if(instance == null) {
            instance = new FinishTaskId(id);
        }
        return instance;
    }

    public static Long getId() {
        return taskId;
    }

    public static void cleanTaskSession() {
        instance = null;
    }
}
