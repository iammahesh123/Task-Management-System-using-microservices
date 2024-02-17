package in.mahesh.tasks.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TaskStatus {
	 PENDING("PENDING"),
	    ASSIGNED("ASSIGNED"),
	    DONE("DONE");


    private final String name;

    TaskStatus (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}