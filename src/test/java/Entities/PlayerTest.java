package Entities;

import org.junit.*;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;
class PlayerTest {

    @Test(timeout = 500)
    void getHealth() {
        Player player = new Player("Test");
        assertEquals(player.getMaxHealth(), 100);
        assertEquals(player.getCurrentHealth(), 100);
    }

    @Test
    void addHealth() {
    }

    @Test
    void getGold() {
    }

    @Test
    void addGold() {
    }

    @Test
    void removeGold() {
    }
}