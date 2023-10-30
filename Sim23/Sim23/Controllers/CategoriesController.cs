using AutoMapper;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Sim23.Data;
using Sim23.Data.Entites;
using Sim23.Helpers;
using Sim23.Models;

namespace Sim23.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CategoriesController : ControllerBase
    {
        private readonly IMapper _mapper;
        private readonly AppEFContext _appEFContext;

        public CategoriesController(IMapper mapper, AppEFContext appEFContext)
        {
            _mapper = mapper;
            _appEFContext = appEFContext;
        }
        [HttpGet("list")]
        public async Task<IActionResult> Get()
        {
            Thread.Sleep(1000);
            var model = await _appEFContext.Categories
                .Where(x=>x.IsDeleted==false)
                .OrderBy(x=>x.Priority)
                .Select(x=>_mapper.Map<CategoryItemViewModel>(x))
                .ToListAsync();
           
            return Ok(model);
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> GetByUd(int id)
        {
            var category = await _appEFContext.Categories
                .Where(x => x.IsDeleted == false)
                .SingleOrDefaultAsync(x=>x.Id==id);
            if (category is null)
                return NotFound();

            return Ok(_mapper.Map<CategoryItemViewModel>(category));
        }

        [HttpPost("create")]
        public async Task<IActionResult> Create([FromBody] CategoryCreateItemVM model)
        {
            try
            {
                var cat = _mapper.Map<CategoryEntity>(model);
                cat.Image = ImageWorker.SaveImage(model.ImageBase64);
                await _appEFContext.Categories.AddAsync(cat);
                await _appEFContext.SaveChangesAsync();
                return Ok(_mapper.Map<CategoryItemViewModel>(cat));

            }catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }
        [HttpPut("update")]
        public async Task<IActionResult> Put([FromBody] CategoryUpdateeItemVM model)
        {
            var cat = await _appEFContext.Categories.FindAsync(model.Id);
            if (cat == null)
                return NotFound();
            else
            {
                cat.Name = model.Name;
                cat.Description = model.Description;
                cat.Priority = model.Priority;
                if (!string.IsNullOrEmpty(model.ImageBase64))
                {
                    ImageWorker.RemoveImage(cat.Image);
                    cat.Image = ImageWorker.SaveImage(model.ImageBase64);
                }
                _appEFContext.Update(cat);
                _appEFContext.SaveChanges();
            }
            return Ok();
        }
        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(int id)
        {
            var category = await _appEFContext.Categories.FindAsync(id);
            if (category is null)
                return NotFound();
            else
            {
                category.IsDeleted = true;
                _appEFContext.SaveChanges();
                return Ok();
            }
        }
    }
}
