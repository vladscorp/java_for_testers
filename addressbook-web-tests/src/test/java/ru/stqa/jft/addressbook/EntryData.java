package ru.stqa.jft.addressbook;

public record EntryData(String firstname, String middlename, String lastname, String nickname, String title,
                        String company, String address, String home, String mobile, String email, String bday,
                        String bmonth, String byear) {
}