using Sim23.Data.Entites.Identity;

namespace Sim23.Abastract
{
    public interface IJwtTokenService
    {
        Task<string> CreateToken(UserEntity user);
    }
}
