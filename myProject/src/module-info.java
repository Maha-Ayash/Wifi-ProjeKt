module myProject {
	exports application;
	exports model;
	exports controller;
	exports database;
	exports repository;
	
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
    requires transitive javafx.graphics;
	requires java.desktop;
	requires jdk.javadoc;
	requires jakarta.persistence;
	requires org.apache.derby.engine;
	requires java.sql;
	requires java.xml.crypto;
	requires org.junit.jupiter.api;
	requires junit;
	
	opens controller to javafx.fxml;
	opens application to javafx.graphics, javafx.fxml;
	opens model to eclipselink;
	
}