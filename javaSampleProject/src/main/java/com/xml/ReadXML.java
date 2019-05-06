package com.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class ReadXML {
	public List<XMLPOJO> list;

	public List<XMLPOJO> getList() {
		return list;
	}

	private final String TABLE = "Table";
	private final String ID = "id";
	private final String STATE = "State";
	private final String DISTRICT = "District";
	private final String MARKET = "Market";
	private final String COMMODITY = "Commodity";
	private final String VARIETY = "Variety";
	private final String ARRIVAL_DATE = "Arrival_Date";
	private final String MIN_PRICE = "Min_x0020_Price";
	private final String MAX_PRICE = "Max_x0020_Price";
	private final String MODEL_PRICE = "Modal_x0020_Price";

	private void read() {
		
		list = new ArrayList<XMLPOJO>();

		try {

			XMLInputFactory factory = XMLInputFactory.newInstance();
			InputStream in = new FileInputStream(
					"C:\\Users\\pandehar\\Harshal_Workspace\\XMLProject\\content\\Date-Wise-Prices-all-Commodity.xml");
			XMLEventReader reader = factory.createXMLEventReader(in);
			XMLPOJO xmlPojo = null;

			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();

					if (startElement.getName().getLocalPart().equals(TABLE)) {

						xmlPojo = new XMLPOJO();

						Iterator<Attribute> attributeIterator = startElement.getAttributes();

						while (attributeIterator.hasNext()) {
							Attribute attribute = attributeIterator.next();
							if (attribute.getName().getLocalPart().equals(ID)) {
								xmlPojo.setId(attribute.getValue());
							}
						}
					}

					if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart().equals(STATE)) {
							event = reader.nextEvent();
							xmlPojo.setState(event.asCharacters().getData());
							continue;
						}
					}

					if (event.asStartElement().getName().getLocalPart().equals(DISTRICT)) {
						event = reader.nextEvent();
						xmlPojo.setDistrict(event.asCharacters().getData());
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(MARKET)) {
						event = reader.nextEvent();
						xmlPojo.setMarket(event.asCharacters().getData());
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(COMMODITY)) {
						event = reader.nextEvent();
						xmlPojo.setCommodity(event.asCharacters().getData());
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(VARIETY)) {
						event = reader.nextEvent();
						xmlPojo.setVariety(event.asCharacters().getData());
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(ARRIVAL_DATE)) {
						event = reader.nextEvent();
						xmlPojo.setArrival_date(event.asCharacters().getData());
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(MIN_PRICE)) {
						event = reader.nextEvent();
						xmlPojo.setMin_price(event.asCharacters().getData());
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(MAX_PRICE)) {
						event = reader.nextEvent();
						xmlPojo.setMax_price(event.asCharacters().getData());
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(MODEL_PRICE)) {
						event = reader.nextEvent();
						xmlPojo.setModel_price(event.asCharacters().getData());
						continue;
					}

					// END Element </Table>
					if (event.isEndElement()) {
						EndElement endElement = event.asEndElement();
						if (endElement.getName().getLocalPart().equals(TABLE)) {
							list.add(xmlPojo);
						}
					}
				}
			}
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ReadXML readXml = new ReadXML();
		readXml.read();
		System.out.println(readXml.getList().size());
	}

}
