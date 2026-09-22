import java.util.List;

public class Main {

    public static void main(String[] args) {

        LegacyBulb rawBulb = new LegacyBulb();
        LegacyThermostat rawThermostat = new LegacyThermostat();

        BulbAdapter bulbAdapter = new BulbAdapter(rawBulb);
        ThermostatAdapter thermostatAdapter =
                new ThermostatAdapter(rawThermostat);

        List<SmartDevice> deviceList =
                List.of(bulbAdapter, thermostatAdapter);

        ModernHub hub = new ModernHub(deviceList);

        System.out.println("=== ACTIVATE ALL ===");

        hub.activateAll();

        System.out.println("Bulb brightness: "
                + rawBulb.readBrightness());

        System.out.println("Thermostat state: "
                + rawThermostat.checkDial());

        System.out.println("Bulb is on: "
                + bulbAdapter.isOn());

        System.out.println("Thermostat is on: "
                + thermostatAdapter.isOn());

        System.out.println("Average power: "
                + hub.calculateAveragePowerUsage());

        // ModernHub badHub = new ModernHub(List.of(rawBulb)); // COMPILE ERROR
        /*
         * The compiler rejects this because LegacyBulb does not implement
         * SmartDevice. The Object Adapter solves this by wrapping LegacyBulb
         * inside BulbAdapter, which implements SmartDevice.
         */

        System.out.println("=== FAULT TEST ===");

        rawBulb.breakFilament();

        System.out.println("Broken bulb is on: "
                + bulbAdapter.isOn());

        System.out.println("Broken bulb power: "
                + bulbAdapter.getPowerPercent());

        rawThermostat.rotateDial("STUCK");

        System.out.println("Corrupted thermostat is on: "
                + thermostatAdapter.isOn());

        System.out.println("Corrupted thermostat power: "
                + thermostatAdapter.getPowerPercent());

        System.out.println("=== EMERGENCY SHUTDOWN ===");

        hub.emergencyShutdown();

        System.out.println("Bulb brightness after shutdown: "
                + rawBulb.readBrightness());

        System.out.println("Thermostat after shutdown: "
                + rawThermostat.checkDial());

        System.out.println("Average power after shutdown: "
                + hub.calculateAveragePowerUsage());
    }
}