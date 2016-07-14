package ac.incheon.custom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ac.incheon.custom.dao.BoardRepository;
import ac.incheon.custom.model.BoardModel;
import ac.incheon.custom.model.JoinModel;

@Service("boardService")
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    
       	
	public BoardModel getBoardInfo(int id)
    {
    	return boardRepository.getBoardInfo(id);
    }
	
	
	public List<BoardModel> getBoardList(String name)
    {
    	return boardRepository.getBoardList(name);
    }
	
	
	@Transactional
	public int insertBoard(BoardModel boardModel) throws Exception
    {
		int insertCount = boardRepository.insertBoard(boardModel);
		
		if( insertCount == 0 )
		{
			throw new RuntimeException("등록되지 않았습니다.");
		}
		return insertCount;
    }
	
	
	@Transactional
	public int deleteBoard(int id)
    {
		return boardRepository.deleteBoard(id);
    }
	
	@Transactional
	public int joinBoard(JoinModel joinModel) throws Exception
    {
		int insertCount2 = boardRepository.joinBoard(joinModel);
		
		
		if( insertCount2 == 0 )
		{
			throw new RuntimeException("회원가입 쿼리가 처리 되지 않았습니다.");
		}
		return insertCount2;
    }
	
	@Transactional
	public BoardModel getPasswd(String user_id) throws Exception
    {
		
	
		return boardRepository.getPasswd(user_id);
    }
	
	@Transactional
	public int updateBoard(BoardModel boardModel) throws Exception
    {
		int insertCount = boardRepository.updateBoard(boardModel);
		
		if( insertCount == 0 )
		{
			throw new RuntimeException("updateboard 쿼리 처리 되지 않았습니다.");
		}
		return insertCount;
    }
	
	
	   public List<BoardModel> getSearchList(String name)
	    {
	       return boardRepository.getSearchList(name);
	    }
	
	
}
