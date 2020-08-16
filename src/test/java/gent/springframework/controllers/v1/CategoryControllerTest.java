package gent.springframework.controllers.v1;

import gent.springframework.api.v1.model.CategoryDTO;
import gent.springframework.controllers.RestResponseEntityExceptionHandler;
import gent.springframework.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


class CategoryControllerTest {

    public static final String NAME1 = "Jim";
    public static final String NAME2 = "SIM";

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
    }

    @Test
    void testListCategories() throws Exception{

        CategoryDTO category1 = new CategoryDTO();
        category1.setName(NAME1);
        category1.setId(1L);

        CategoryDTO category2 = new CategoryDTO();
        category2.setName(NAME2);
        category2.setId(2L);

        List<CategoryDTO> categories = Arrays.asList(category1,category2);

        when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get(CategoryController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories",hasSize(2)));

    }

    @Test
    void getCategoryByName() throws Exception {

        CategoryDTO category1 = new CategoryDTO();
        category1.setName(NAME1);
        category1.setId(1L);

        when(categoryService.getCategoryByName(anyString())).thenReturn(category1);

        mockMvc.perform(get(CategoryController.BASE_URL+ "/Jim")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",equalTo(NAME1)));
    }
}