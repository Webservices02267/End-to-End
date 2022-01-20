package dtu.services;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class BankClient extends AbstractClient {



    public static class Account {
        public String accountId;
        public User user;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("account{");
            sb.append("accountId:'").append(accountId).append('\'');
            sb.append(", user:").append(user);
            sb.append('}');
            return sb.toString();
        }
    }

    public static class User {
        public String firstName;
        public String lastName;
        public String cprNumber;

        public User() {}

        public User(String firstName, String lastName, String cprNumber) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.cprNumber = cprNumber;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("user{");
            sb.append("firstName:'").append(firstName).append('\'');
            sb.append(", lastName:'").append(lastName).append('\'');
            sb.append(", cprNumber:'").append(cprNumber).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    public static class CreateAccount {
        public int balance;
        public User user;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("{");
            sb.append("balance:").append(balance);
            sb.append(", user:").append(user);
            sb.append('}');
            return sb.toString();
        }
    }

    public Response createAccountAndGetId(int balance, User user) {
        var ca = new CreateAccount();
        ca.balance = balance;
        ca.user = user;
        return client.target("http://fm-00.compute.dtu.dk:80")
                .path("/rest/accounts")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_PLAIN)
                .post(Entity.json(ca));
    }

    public Response deleteAccount(String accountId) {
        return client.target("http://fm-00.compute.dtu.dk:80")
                .path("/rest/accounts/" + accountId)
                .request(MediaType.TEXT_PLAIN)
                .accept(MediaType.TEXT_PLAIN)
                .delete();
    }

    public Response getAccount(String accountId) {
        return client.target("http://fm-00.compute.dtu.dk:80")
                .path("/rest/accounts/" + accountId)
                .request(MediaType.TEXT_PLAIN)
                .accept(MediaType.APPLICATION_JSON)
                .delete();
    }


    public Account[] getAccounts() {
        return client.target("http://fm-00.compute.dtu.dk:80")
                .path("/rest/accounts")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .get(Account[].class);
    }



}
