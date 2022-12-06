module fr.f1parking {

	requires transitive javafx.graphics;
	requires org.apache.logging.log4j;
	requires com.fasterxml.jackson.databind;
	requires javafx.controls;
	requires javafx.media;
	requires org.junit.jupiter.api;

	exports fr.f1parking.ui;
}