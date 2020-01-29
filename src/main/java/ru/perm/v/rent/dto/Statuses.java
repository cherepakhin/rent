package ru.perm.v.rent.dto;

import java.util.ArrayList;
import java.util.List;
import ru.perm.v.rent.model.Status;

public class Statuses {

	private List<Status> statuses = new ArrayList<>();

	public Statuses(List<Status> statuses) {
		this.statuses = statuses;
	}

	public List<Status> getStatuses() {
		return statuses;
	}
}
