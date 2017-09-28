
function user(firstName, lastName, address, phoneNumber, email, password, isAdmin)
{
   this.firstName = firstName;
    this.lastName =lastName;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.password = password;
    this.isAdmin = isAdmin;
};

module.exports =user;