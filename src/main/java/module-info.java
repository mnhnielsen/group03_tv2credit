module Creditsystem{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires com.google.gson;

    opens Creditsystem.Data;
    opens Creditsystem.Presentation;
    opens Creditsystem.Domain;
}