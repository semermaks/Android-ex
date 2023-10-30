namespace Sim23.Models
{
    public class CategoryItemViewModel
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public int Priority { get; set; }
        public string Description { get; set; }
        public string Image { get; set; }
    }

    public class CategoryCreateItemVM
    {
        public string Name { get; set; }
        public string ImageBase64 { get; set; }
        public int Priority { get; set; }
        public string Description { get; set; }
    }
    public class CategoryUpdateeItemVM
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string ImageBase64 { get; set; }
        public int Priority { get; set; }
        public string Description { get; set; }
    }
}
