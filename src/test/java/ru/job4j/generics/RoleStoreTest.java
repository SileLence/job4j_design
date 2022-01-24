package ru.job4j.generics;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindRole() {
        RoleStore store = new RoleStore();
        Role expected = new Role("1", "TestRole");
        store.add(expected);
        Role actual = store.findById("1");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void whenCantFindRole() {
        RoleStore store = new RoleStore();
        Role role = new Role("1", "RoleName");
        store.add(role);
        Role expected = store.findById("5");
        Assert.assertNull(expected);
    }
    @Test
    public void whenAddDuplicateAndFindRole() {
        RoleStore store = new RoleStore();
        Role first = new Role("1", "First Role");
        Role second = new Role("1", "Second Role");
        store.add(first);
        store.add(second);
        Role actual = store.findById("1");
        assertThat(actual.getName(), is("First Role"));
    }

    @Test
    public void whenAddThenReplaceThenFindReplacedRole() {
        RoleStore store = new RoleStore();
        Role first = new Role("1", "First Role");
        Role second = new Role("2", "Second Role");
        store.add(first);
        store.replace("1", second);
        Role actual = store.findById("1");
        assertThat(actual.getName(), is("Second Role"));
    }

    @Test
    public void whenCantReplaceRole() {
        RoleStore store = new RoleStore();
        Role first = new Role("1", "1 Role");
        Role second = new Role("1", "2 Role");
        store.add(first);
        boolean actual1 = store.replace("3", second);
        Role actual2 = store.findById("1");
        assertFalse(actual1);
        assertThat(actual2.getName(), is("1 Role"));
    }

    @Test
    public void whenAddAndDeleteRole() {
        RoleStore store = new RoleStore();
        Role role = new Role("1", "Role Name");
        store.add(role);
        boolean actual1 = store.delete("1");
        assertTrue(actual1);
        Role actual2 = store.findById("1");
        assertNull(actual2);
    }

    @Test
    public void whenCantDeleteRole() {
        RoleStore store = new RoleStore();
        Role role = new Role("1", "Role Name");
        boolean actual = store.delete("2");
        Assert.assertFalse(actual);
    }
}
