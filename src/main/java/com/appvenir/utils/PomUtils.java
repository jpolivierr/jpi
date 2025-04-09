package com.appvenir.utils;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class PomUtils {

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
            System.err.println("Failed to read groupId from pom.xml: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        File pomFile = new File("path/to/your/pom.xml");
        String groupId = getGroupId(pomFile);
        System.out.println("groupId: " + groupId);
    }
}

