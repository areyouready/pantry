package de.fnortheim.pantry.presentation;

import org.junit.jupiter.api.Test;

class PantryTest {

    @Test
    void dateSort() {
        Pantry pantry = new Pantry();
        pantry.dateSort(" ", "20.05.2018");
        pantry.dateSort("20.05.2018", " ");
    }
}