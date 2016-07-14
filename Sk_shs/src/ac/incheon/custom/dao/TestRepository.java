package ac.incheon.custom.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;

import ac.incheon.custom.model.TestModel;

public interface TestRepository {

	List<TestModel> getSelectQuery();
	
	List<TestModel> getSelectQueryByName(@Param("name")String name);
	
	
	int insertQuery(TestModel insertModel);
	
	int updateQuery(TestModel insertModel);
	
	int deleteQuery(TestModel insertModel);
}
