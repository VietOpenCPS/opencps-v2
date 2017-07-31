
package org.mobilink.api.model;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.opencps.api.model package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.opencps.api.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DictItemResults }
     * 
     */
    public DictItemResults createDictItemResults() {
        return new DictItemResults();
    }

    /**
     * Create an instance of {@link DictItemModel }
     * 
     */
    public DictItemModel createDictItemModel() {
        return new DictItemModel();
    }

    /**
     * Create an instance of {@link DictItemList }
     * 
     */
    public DictItemList createDictItemList() {
        return new DictItemList();
    }

    /**
     * Create an instance of {@link DictCollectionModel }
     * 
     */
    public DictCollectionModel createDictCollectionModel() {
        return new DictCollectionModel();
    }

    /**
     * Create an instance of {@link DictCollectionResults }
     * 
     */
    public DictCollectionResults createDictCollectionResults() {
        return new DictCollectionResults();
    }

    /**
     * Create an instance of {@link DictCollectionList }
     * 
     */
    public DictCollectionList createDictCollectionList() {
        return new DictCollectionList();
    }
    


}
