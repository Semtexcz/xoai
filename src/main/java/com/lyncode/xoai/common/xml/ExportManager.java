/**
 * Copyright 2012 Lyncode
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lyncode.xoai.common.xml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.lyncode.xoai.common.core.XOAIManager;
import com.lyncode.xoai.common.exceptions.OAIException;
import com.lyncode.xoai.common.xml.oaipmh.OAIPMHtype;
import com.lyncode.xoai.common.xml.xoaidescription.XOAIDescription;


/**
 * @author DSpace @ Lyncode
 * @version 1.0.1
 */
public class ExportManager {
    private final static QName _OAIPMH_QNAME = new QName("http://www.openarchives.org/OAI/2.0/", "OAI-PMH");
    private static Logger log = LogManager.getLogger(ExportManager.class);

    private Map<String, String> _values;
    // private Configuration _config;

    public static String export (String cont, Object obj, PrefixMapper mapper) {
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            JAXBContext context = JAXBContext.newInstance(cont);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper", mapper);
            marshaller.marshal(obj, output);
            return output.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
        } catch (JAXBException ex) {
            log.error(ex.getMessage(), ex);
            return "";
        }
    }

    public static String export (XOAIDescription xml) {
        return export("com.lyncode.xoai.common.xml.xoaidescription", xml, new PrefixMapper());
    }

    public ExportManager () {
        _values = new HashMap<String, String>();
    }

    public void addMap (String id, String value) {
        _values.put(id, value);
    }




    public void export (OAIPMHtype oai, OutputStream out) throws OAIException {
        try {

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            JAXBContext context = JAXBContext.newInstance("com.lyncode.xoai.common.xml.oaipmh");
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            JAXBElement<OAIPMHtype> type = new JAXBElement<OAIPMHtype>(_OAIPMH_QNAME, OAIPMHtype.class, null, oai);
            marshaller.marshal(type, output);
            String outS = output.toString();
            for (String id : _values.keySet())
                outS = outS.replace(id, _values.get(id));
            if (XOAIManager.getManager().isIdentated())
                out.write(this.format(outS).getBytes());
            else
                out.write(outS.getBytes());

            
        } catch (JAXBException ex) {
            log.error(ex.getMessage(), ex);
            throw new OAIException(ex);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
            throw new OAIException(ex);
        }
    }

    private String format(String unformattedXml) {
        Document document = parseXmlFile(unformattedXml);

		if (document == null) return "";
		
		Writer out = new StringWriter();
		TransformerFactory tfactory = TransformerFactory.newInstance();
		Transformer serializer;
		try {
		    serializer = tfactory.newTransformer();
		    //Setup indenting to "pretty print"
		    serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		    serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		    
		    serializer.transform(new DOMSource(document), new StreamResult(out));
		    return out.toString();
		} catch (TransformerException e) {
		    // this is fatal, just dump the stack and throw a runtime exception
		    return "";
		}
    }

    private Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (ParserConfigurationException e) {
            return null;
        } catch (SAXException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}
