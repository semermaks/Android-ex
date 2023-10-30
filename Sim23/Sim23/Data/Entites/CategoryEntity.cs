using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Sim23.Data.Entites
{
    [Table("tblCategories")]
    public class CategoryEntity : BaseEntity<int>
    {
        [Required, StringLength(255)]
        public string Name { get; set; }
        public int Priority { get; set; }
        [StringLength(255)]
        public string Image { get; set; }
        [StringLength(4000)] 
        public string Description { get; set; }
    }
}
