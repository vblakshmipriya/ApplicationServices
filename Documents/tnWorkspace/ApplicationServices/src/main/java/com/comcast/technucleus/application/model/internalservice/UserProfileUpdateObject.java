package com.comcast.technucleus.application.model.internalservice;

public class UserProfileUpdateObject {
	
    private String FulfillmentCenter;

    public String getFulfillmentCenter ()
    {
        return FulfillmentCenter;
    }

    public void setFulfillmentCenter (String FulfillmentCenter)
    {
        this.FulfillmentCenter = FulfillmentCenter;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [FulfillmentCenter = "+FulfillmentCenter+"]";
    }
}

