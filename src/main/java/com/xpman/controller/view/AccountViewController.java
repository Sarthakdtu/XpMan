package com.xpman.controller.view;

import com.xpman.model.Account;
import com.xpman.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("account")
public class AccountViewController {

    private final Logger logger = LoggerFactory.getLogger(AccountViewController.class);

    @Autowired
    AccountService accountService;

    @GetMapping("/health")
    public String health(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        try {
            model.addAttribute("name", name);
            return "account/health";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }

    @GetMapping("/create-form")
    public String createForm(Model model) {
        try {
            logger.info("Requesting account creation form");
            model.addAttribute("account", new Account());
            return "account/create/create-form";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }

    @GetMapping("/all-accounts")
    public String getAllAccounts(Model model) {
        try {
            logger.info("Get all accounts");
            List<Account> accounts = accountService.read();
            model.addAttribute("accounts", accounts);
            return "account/read/all-accounts";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }

    @PostMapping("/create")
    public String accountFormSubmit(@ModelAttribute Account account, Model model) {
        try {
            accountService.write(account);
            model.addAttribute("account", account);
            logger.info("Account created :{}", account.toString());
            return "account/create/create-success";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }

    @GetMapping("/edit-form/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        try {
            Account account = accountService.getAccountById(id);
            logger.info("Edit form requested for Account {} with id {} ", account.getName(), id);
            model.addAttribute("account", account);
            return "account/update/edit-form";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }

    @PostMapping("/edit")
    public String editFormSubmit(@ModelAttribute Account account, Model model) {
        logger.info("Account Edit Request Received");
        if(account == null){
            logger.info("Account is null");
            model.addAttribute("error", "Account is null. Please try again with correct values");
            return "common/client-error";
        }
        else if(account.getId() == null ||
            account.getName() == null ||
            account.getAmount() == null ||
            account.getColor() == null){
            logger.info("Empty fields {} ", account.toString());
            model.addAttribute("error", "Some fields are null. Please try again with correct values");
            return "common/client-error";
        }
        try {
            accountService.updateAccountById(account);
            model.addAttribute("account", account);
            logger.info("Account edited :{}", account);
            return "account/update/edit-success";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }

    @GetMapping("/delete-confirmation/{id}")
    public String deleteConfirmation(@PathVariable Integer id, Model model) {
        logger.info("Account Delete Confirmation Request Received");
        try {
            Account account = accountService.getAccountById(id);
            logger.info("Delete requested for Account {} with id {} ", account.getName(), id);
            model.addAttribute("account", account);
            return "account/delete/delete-confirmation";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute Account account, Model model) {
        if(account.getId() == null){
            logger.info("Account or Account Id is null");
            model.addAttribute("error", "Account or Account Id is null");
            return "common/client-error";
        }
        try {
            accountService.deleteAccountById(account.getId());
            logger.info("Account deleted with id :{}", account.getId());
            return "account/delete/delete-success";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }
}
