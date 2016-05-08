package com.comcast.technucleus.application.router;

import java.util.List;
import java.util.concurrent.Future;

import com.comcast.technucleus.application.model.ContactInfo;

public interface ContactServiceRouter 
{
	public  Future<List<ContactInfo>> getContactList(String accountNumber , String date);

}
