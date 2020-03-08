package com.thc.config;

import com.thc.pojo.Configuration;
import com.thc.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : tanghaochen
 * create at:  2020-02-23  19:14
 * @program IPersistence_test
 * @description:
 */
public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream stream) throws DocumentException {
        Document document = new SAXReader().read(stream);

        Element rootElement = document.getRootElement();
        List<Element> list = rootElement.selectNodes("//select");
        String namespace = rootElement.attributeValue("namespace");

        List<Element> addList = rootElement.selectNodes("//insert");
        List<Element> updateList = rootElement.selectNodes("//update");
        List<Element> deleteList = rootElement.selectNodes("//delete");

        Set<List<Element>> set = new LinkedHashSet<>();
        set.add(list);
        set.add(addList);
        set.add(updateList);
        set.add(deleteList);

        for (List<Element> elements : set) {
            for (Element element : elements) {
                String id = element.attributeValue("id");
                String resultType = element.attributeValue("resultType");
                String paramterType = element.attributeValue("paramterType");
                String sqlText = element.getTextTrim();

                MappedStatement mappedStatement = new MappedStatement();
                mappedStatement.setId(id);
                mappedStatement.setParamterType(paramterType);
                mappedStatement.setResultType(resultType);
                mappedStatement.setSql(sqlText);
                String key = namespace + "." + id;
                configuration.getMap().put(key, mappedStatement);
            }
        }
    }
}
