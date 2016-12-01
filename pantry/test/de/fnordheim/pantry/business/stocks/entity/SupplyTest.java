package de.fnordheim.pantry.business.stocks.entity;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by sebastianbasner on 16.09.16.
 */
public class SupplyTest {

    private Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();
    }

    @Test
    public void prereqsMet() {
//        Supply validFreezerSupply = new Supply("test", 2, 3, new Date(), new Date(), SupplyType.FREEZER);
//        Set<ConstraintViolation<Supply>> freezerViolations = this.validator.validate(validFreezerSupply);
//        assertTrue(freezerViolations.isEmpty());
//        Supply invalidFreezerSupply = new Supply("t", 2, 3, new Date(), new Date(), SupplyType.FREEZER);
//        freezerViolations = this.validator.validate(invalidFreezerSupply);
//        assertFalse(freezerViolations.isEmpty());

//        Supply validPantrySupply = new Supply("test", 2, 3, new Date(), SupplyType.PANTRY);
//        Set<ConstraintViolation<Supply>> pantryViolations = this.validator.validate(validPantrySupply);
//        assertTrue(pantryViolations.isEmpty());
//        Supply invalidPantrySupply = new Supply("t", 2, 3, new Date(), SupplyType.PANTRY);
//        pantryViolations = this.validator.validate(invalidPantrySupply);
//        assertFalse(pantryViolations.isEmpty());
        PantrySupply validPantrySupply = new PantrySupply("test", 2, 3, new Date());
        Set<ConstraintViolation<Supply>> pantryViolations = this.validator.validate(validPantrySupply);
        assertTrue(pantryViolations.isEmpty());
        PantrySupply invalidPantrySupply = new PantrySupply("t", 2, 3, new Date());
        pantryViolations = this.validator.validate(invalidPantrySupply);
        assertFalse(pantryViolations.isEmpty());
    }

}