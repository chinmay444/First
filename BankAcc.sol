// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract BankAccount {
    address public owner;
    uint public balance;

    constructor() {
        owner = msg.sender;
        balance = 0;
    }

    function deposit(uint amount) public {
        require(msg.sender == owner, "Only the owner can deposit.");
        balance += amount;
    }

    function withdraw(uint amount) public {
        require(msg.sender == owner, "Only the owner can withdraw.");
        require(amount <= balance, "Insufficient balance.");
        balance -= amount;
    }

    function showBalance() public view returns (uint) {
        return balance;
    }
}
