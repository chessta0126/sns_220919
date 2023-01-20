package com.sns.timeline.dao;

import java.util.List;

import com.sns.timeline.model.CardView;

public interface TimelineDAO {
	public List<CardView> generateCardList();
}
