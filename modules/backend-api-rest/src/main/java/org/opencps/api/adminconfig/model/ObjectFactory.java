//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.04.04 at 10:50:48 PM ICT 
//


package org.opencps.api.adminconfig.model;

import javax.xml.bind.annotation.XmlRegistry;



/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ApiManagerDetailModel }
     * 
     */
    public ApiManagerDetailModel createApiManagerDetailModel() {
        return new ApiManagerDetailModel();
    }

    /**
     * Create an instance of {@link ApiRoleResultsModel }
     * 
     */
    public ApiRoleResultsModel createApiRoleResultsModel() {
        return new ApiRoleResultsModel();
    }

    /**
     * Create an instance of {@link ApiRoleModel }
     * 
     */
    public ApiRoleModel createApiRoleModel() {
        return new ApiRoleModel();
    }

    /**
     * Create an instance of {@link ApiManagerInputModel }
     * 
     */
    public ApiManagerInputModel createApiManagerInputModel() {
        return new ApiManagerInputModel();
    }

    /**
     * Create an instance of {@link ApiManagerResultsModel }
     * 
     */
    public ApiManagerResultsModel createApiManagerResultsModel() {
        return new ApiManagerResultsModel();
    }

    /**
     * Create an instance of {@link ApiManagerModel }
     * 
     */
    public ApiManagerModel createApiManagerModel() {
        return new ApiManagerModel();
    }

    /**
     * Create an instance of {@link ApiRoleDetailModel }
     * 
     */
    public ApiRoleDetailModel createApiRoleDetailModel() {
        return new ApiRoleDetailModel();
    }

    /**
     * Create an instance of {@link ApiRoleInputModel }
     * 
     */
    public ApiRoleInputModel createApiRoleInputModel() {
        return new ApiRoleInputModel();
    }
    
    public SyncTrackingQuery createSyncTrackingQuery() {
    	return new SyncTrackingQuery();
    }
    
    public DtoResponse createDtoResponse() {
    	return new DtoResponse();
    }
    
    public SyncTrackingResponse createSyncTrackingResponse() {
    	return new SyncTrackingResponse();
    }

}
