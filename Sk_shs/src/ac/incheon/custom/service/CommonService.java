package ac.incheon.custom.service;

import org.springframework.stereotype.Service;


@Service("commonService")


public class CommonService {
	
	public String getUserPassword(String userId)
	{	
		return userId;
	}

}
