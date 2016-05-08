package com.comcast.technucleus.application.services.client;

import java.util.List;

import com.comcast.technucleus.application.model.ContactInfo;

public interface ContactServiceClient 
{
   public List<ContactInfo> getContactList(String accountNumber , String Date);
	
}
