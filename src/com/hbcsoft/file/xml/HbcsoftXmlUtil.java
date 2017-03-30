package com.hbcsoft.file.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.yaja.common.constant.YAJAConstant;

public class HbcsoftXmlUtil {
	
	public void read() throws HbcsoftException {
		read(YAJAConstant.SQLCONFIG_FILENAME, false);
	}
	
	public void read(final String fileName) throws HbcsoftException {
		read(fileName, false);
	}

	public void read(final String fileName, final boolean bool) throws HbcsoftException {
		read(fileName, bool, "");
	}

	public void read(final String fileName, final boolean bool, final String node) throws HbcsoftException {
		read(fileName, bool, node, YAJAConstant.SYSMAP);
	}

	public void read(final String fileName, final boolean bool, final String node, final Map<String, String> map) throws HbcsoftException {
		if (bool)
			zdy(fileName, node, map, bool);
		else
			zdy(fileName, node, map);
	}

	private void zdy(final String fileName, final String node, final Map<String, String> map) throws HbcsoftException {
		try {
			final SAXReader reader = new SAXReader();
			final InputStream in = HbcsoftXmlUtil.class.getResourceAsStream(fileName);
			final org.dom4j.Document doc = reader.read(in);
			final Element root = doc.getRootElement();
			zdy(root, node, map);
		} catch (DocumentException e) {
			Logger.getLogger(this.getClass()).error(e);
			throw new HbcsoftException("HbcsoftXmlUtil-->read", 999, e);
		}
	}

	@SuppressWarnings("unchecked")
	private void zdy(final Element root, final String node, final Map<String, String> map) throws HbcsoftException {
		if (root == null)
			return;

		final List<Attribute> attrs = root.attributes();
		if (!attrs.isEmpty()) {
			for (int index = 0; index < attrs.size(); index++) {
				if (!node.isEmpty() && !node.equals(attrs.get(index).getName()))
					continue;

				HbcsoftCache.setSqlValue(attrs.get(index).getParent().getName() + 
						YAJAConstant.CHARACTER_UNDERLINE + attrs.get(index).getName(), attrs.get(index).getValue());
			}
		}
		final List<Element> childNodes = root.elements();
		for (int index = 0; index < childNodes.size(); index++) {
			zdy(childNodes.get(index), node, map);
		}
	}

	private void zdy(final String fileName, final String node, final Map<String, String> map, final boolean bool) throws HbcsoftException {
		try {
			final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			final DocumentBuilder builder = dbf.newDocumentBuilder();
			
			final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			
			final InputStream in = classLoader.getResourceAsStream(fileName);
			final org.w3c.dom.Document doc = builder.parse(in);
			final XPathFactory factory = XPathFactory.newInstance();
			final XPath xpath = factory.newXPath();
			final XPathExpression expr = xpath.compile(node);
			final NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < nodes.getLength(); i++) {
				if (map.containsKey(nodes.item(i).getNodeName()))
					if(bool)
						throw new HbcsoftException("HbcsoftXmlUtil-->read", 999, nodes.item(i).getNodeName() + "重复配置");
					else
						continue;
				map.put(nodes.item(i).getNodeName(), nodes.item(i).getNodeValue());
			}
		} catch (XPathExpressionException | ParserConfigurationException | SAXException | IOException e) {
			Logger.getLogger(this.getClass()).error(e);
			throw new HbcsoftException("HbcsoftXmlUtil-->read", 999, e);
		}
	}
}
