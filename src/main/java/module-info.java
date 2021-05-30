module Creditsystem{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires com.google.gson;
    requires org.apache.commons.lang3;
    requires postgresql;

    opens Creditsystem.Data;
    opens Creditsystem.Presentation;
    opens Creditsystem.Domain;
    opens Creditsystem.Domain.Helpers;
}