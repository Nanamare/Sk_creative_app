package ac.incheon.custom.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;

import ac.incheon.custom.model.BoardModel;
import ac.incheon.custom.model.JoinModel;

public interface BoardRepository {
	
	BoardModel getBoardInfo(@Param("id")int id);
	
	List<BoardModel> getBoardList(@Param("boardName")String boardName);
	
	List<BoardModel> getSearchList(@Param("name")String name);
		
	int insertBoard(BoardModel insertModel);
	
	int deleteBoard(@Param("id")int id);

	int joinBoard(JoinModel joinModel);
	
	int updateBoard(BoardModel borderModel);
	
	BoardModel getPasswd(@Param("user_id")String user_id);
	
}
