package com.diets.board.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diets.board.dao.BoardDAO;
import com.diets.board.domain.Board;



@Service
public class BoardService {

	@Autowired
	BoardDAO boardDao;
	
	public void add(Board board) {
		boardDao.add(board);
	}
	public Board get(int id) {
		return boardDao.get(id);
	}
//	public Board get(int id) {
//		return boardDao.get(id);
//	}
//		
	public List<Board> getAll(int page, int count){
		return boardDao.getAll((page - 1) * count, count);
	}
}