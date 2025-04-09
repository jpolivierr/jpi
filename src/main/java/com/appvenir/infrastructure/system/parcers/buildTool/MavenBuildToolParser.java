package com.appvenir.infrastructure.system.parcers.buildTool;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.appvenir.infrastructure.system.logger.Logger;
import com.appvenir.utils.Condition;

public class MavenBuildToolParser implements BuildToolParser{

    @Override
    public String getProjectPackage(String filePath) {
        Condition.notNull(filePath, "File path cannot be null");
        return getGroupId(new File(filePath));
    }

    public static String getGroupId(File pomFile) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(pomFile);
            doc.getDocumentElement().normalize();

            NodeList groupIdNodes = doc.getElementsByTagName("groupId");

            for (int i = 0; i < groupIdNodes.getLength(); i++) {
                Node node = groupIdNodes.item(i);

                // Return the first groupId under the <project> tag
                if (node.getParentNode().getNodeName().equals("project")) {
                    return node.getTextContent().trim();
                }
            }

            return null; // Not found
        } catch (Exception e) {
            Logger.error("Failed to read groupId from pom.xml: " + e.getMessage());
            return null;
        }
    }
    
}
