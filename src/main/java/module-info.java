module Creditsystem{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires com.google.gson;
    requires org.apache.commons.lang3;
    requires postgresql;
    requires java.mail;

    opens Creditsystem.Data;
    opens Creditsystem.Presentation;
    opens Creditsystem.Domain;
}