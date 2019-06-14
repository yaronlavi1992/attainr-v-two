import org.springframework.web.multipart.commons.CommonsMultipartResolver

// Place your Spring DSL code here
beans = {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver()
        multipartResolver.setMaxUploadSize(2000000)
}
