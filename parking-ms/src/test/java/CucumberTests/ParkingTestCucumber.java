package CucumberTests;

import com.delphi.nice.training.reader.JSONReader;
import com.delphi.nice.training.service.ParkingService;
import com.delphi.nice.training.service.ParkingServiceImpl;
import com.delphi.nice.training.writer.JSONWriter;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ParkingTestCucumber {

    private File testFile;

    private ParkingService parkingService;
    private boolean isFreeSlotPresent;

    @Before
    public void before() {
        List<JSONObject> array = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("parkingSlot", 1);
        jsonObject.put("isParked", false);
        array.add(jsonObject);
        testFile = new File("src/test/resources/testParkArea.json");
        new JSONWriter(testFile.getAbsolutePath()).writeToFile(array);
    }

    @Given("initialize file with data")
    public void initialize_file_with_data() {
        this.testFile = new File("src/test/resources/testParkArea.json");
    }

    @When("create instance service class")
    public void create_instance_service_class() {
        parkingService = new ParkingServiceImpl(testFile.getAbsolutePath());
    }

    @And("execute method to take slot")
    public void execute_method_to_take_slot() {
        parkingService.takeFreeParkSpot();
    }

    @Then("field in file should be change on true")
    public void field_in_file_should_be_change_on_true() {
        JSONObject obj = new JSONReader().getJsonArr(testFile.getAbsolutePath()).get(0);
        Assert.assertTrue((boolean) obj.get("isParked"));
    }


    @And("execute method for chek places in file")
    public void execute_method_for_chek_places_in_file() {
        isFreeSlotPresent = parkingService.isFreeSlotPresent();
    }

    @Then("return true if slot is free")
    public void return_true_if_slot_is_free() {
        Assert.assertTrue(isFreeSlotPresent);
    }

    @Then("return false if slot is busy")
    public void return_false_if_slot_is_busy() {
        Assert.assertFalse(isFreeSlotPresent);
    }

}
