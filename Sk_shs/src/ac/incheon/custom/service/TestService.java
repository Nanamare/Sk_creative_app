package ac.incheon.custom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ac.incheon.custom.dao.TestRepository;
import ac.incheon.custom.model.TestModel;

@Service("testService")
public class TestService {

    @Autowired
    private TestRepository testRepository;
    
        
    public TestRepository getTestRepository() {
		return testRepository;
	}

	public void setTestRepository(TestRepository testRepository) {
		this.testRepository = testRepository;
	}
	
	public List<TestModel> getTestList()
    {
    	return testRepository.getSelectQuery();
    }
	
	public List<TestModel> getTestList(String name)
    {
    	return testRepository.getSelectQuery();
    }
	
	public int updateTestModel(TestModel testModel)
    {
    	return testRepository.updateQuery(testModel);
    }
	
	@Transactional
	public int insertTestModel(TestModel testModel) throws Exception
    {
		int insertCount = testRepository.insertQuery(testModel);
		
		if( insertCount == 0 )
		{
			throw new RuntimeException();
		}
		return insertCount;
    }
	
	
	
	public int deleteTestModel(TestModel testModel)
    {
		return testRepository.deleteQuery(testModel);
    }
    
}
