﻿using FluentValidation;
using Microsoft.AspNetCore.Identity;
using Sim23.Data.Entites.Identity;
using Sim23.Models;

namespace Sim23.Validators
{
    public class ValidatorRegisterViewModel : AbstractValidator<RegisterUserViewModel>
    {
        private readonly UserManager<UserEntity> _userManager;
        public ValidatorRegisterViewModel(UserManager<UserEntity> userManager)
        {
            _userManager = userManager;
            RuleFor(x => x.Email)
               .NotEmpty().WithMessage("Поле пошта є обов'язковим!")
               .EmailAddress().WithMessage("Пошта є не коректною!")
               .DependentRules(() =>
               {
                   RuleFor(x => x.Email).Must(BeUniqueEmail)

                    .WithMessage("Дана пошта уже зареєстрована!");
               });
            RuleFor(x => x.Password)
                .NotEmpty().WithName("Password").WithMessage("Поле пароль є обов'язковим!")
                .MinimumLength(5).WithName("Password").WithMessage("Поле пароль має містити міннімум 5 символів!");

            RuleFor(x => x.FirstName)
                .NotEmpty().WithName("FirstName").WithMessage("Поле ім'я є обов'язковим!");

            RuleFor(x => x.LastName)
                .NotEmpty().WithName("SecondName").WithMessage("Поле прізвище є обов'язковим!");

            RuleFor(x => x.ImageBase64)
                .NotEmpty().WithName("ImageBase64").WithMessage("Поле фото є обов'язковим!");



            //.Matches("[A-Z]").WithName("Password").WithMessage("Password must contain one or more capital letters.")
            //.Matches("[a-z]").WithName("Password").WithMessage("Password must contain one or more lowercase letters.")
            //.Matches(@"\d").WithName("Password").WithMessage("Password must contain one or more digits.")
            //.Matches(@"[][""!@$%^&*(){}:;<>,.?/+_=|'~\\-]").WithName("Password").WithMessage("Password must contain one or more special characters.")
            //.Matches("^[^£# “”]*$").WithName("Password").WithMessage("Password must not contain the following characters £ # “” or spaces.");
            RuleFor(x => x.ConfirmPassword)
                .NotEmpty().WithName("ConfirmPassword").WithMessage("Поле є обов'язковим!")
                 .Equal(x => x.Password).WithMessage("Поролі не співпадають!");
        }
        private bool BeUniqueEmail(string email)
        {
            return _userManager.FindByEmailAsync(email).Result == null;
        }
    }
}
