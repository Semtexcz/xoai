//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.06.14 at 01:01:41 AM WEST 
//


package com.lyncode.xoai.common.xml.xoaiconfig;

import javax.xml.bind.annotation.XmlRegistry;

import com.lyncode.xoai.common.xml.xoaiconfig.Configuration.Contexts;
import com.lyncode.xoai.common.xml.xoaiconfig.Configuration.Contexts.Context;
import com.lyncode.xoai.common.xml.xoaiconfig.Configuration.Filters;
import com.lyncode.xoai.common.xml.xoaiconfig.Configuration.Filters.Filter;
import com.lyncode.xoai.common.xml.xoaiconfig.Configuration.Formats;
import com.lyncode.xoai.common.xml.xoaiconfig.Configuration.Formats.Format;
import com.lyncode.xoai.common.xml.xoaiconfig.Configuration.Sets;
import com.lyncode.xoai.common.xml.xoaiconfig.Configuration.Sets.Set;
import com.lyncode.xoai.common.xml.xoaiconfig.Configuration.Transformers;
import com.lyncode.xoai.common.xml.xoaiconfig.Configuration.Transformers.Transformer;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.lyncode.xoai.schemas.xoaiconfig package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.lyncode.xoai.schemas.xoaiconfig
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Format }
     * 
     */
    public Format createConfigurationFormatsFormat() {
        return new Format();
    }

    /**
     * Create an instance of {@link Set }
     * 
     */
    public Set createConfigurationSetsSet() {
        return new Set();
    }

    /**
     * Create an instance of {@link BundleReference }
     * 
     */
    public BundleReference createBundleReference() {
        return new BundleReference();
    }

    /**
     * Create an instance of {@link Parameter }
     * 
     */
    public Parameter createParameter() {
        return new Parameter();
    }

    /**
     * Create an instance of {@link Transformers }
     * 
     */
    public Transformers createConfigurationTransformers() {
        return new Transformers();
    }

    /**
     * Create an instance of {@link Formats }
     * 
     */
    public Formats createConfigurationFormats() {
        return new Formats();
    }

    /**
     * Create an instance of {@link Configuration }
     * 
     */
    public Configuration createConfiguration() {
        return new Configuration();
    }

    /**
     * Create an instance of {@link Transformer }
     * 
     */
    public Transformer createConfigurationTransformersTransformer() {
        return new Transformer();
    }

    /**
     * Create an instance of {@link Sets }
     * 
     */
    public Sets createConfigurationSets() {
        return new Sets();
    }

    /**
     * Create an instance of {@link Contexts }
     * 
     */
    public Contexts createConfigurationContexts() {
        return new Contexts();
    }

    /**
     * Create an instance of {@link Filters }
     * 
     */
    public Filters createConfigurationFilters() {
        return new Filters();
    }

    /**
     * Create an instance of {@link Filter }
     * 
     */
    public Filter createConfigurationFiltersFilter() {
        return new Filter();
    }

    /**
     * Create an instance of {@link Context }
     * 
     */
    public Context createConfigurationContextsContext() {
        return new Context();
    }

}
