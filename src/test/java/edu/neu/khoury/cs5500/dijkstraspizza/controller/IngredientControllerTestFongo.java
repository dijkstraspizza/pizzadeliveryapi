package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import edu.neu.khoury.cs5500.dijkstraspizza.FakeMongo;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Import(value={FakeMongo.class, Database.class})
public class IngredientControllerTestFongo {
}
