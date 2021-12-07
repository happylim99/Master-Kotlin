package com.sean.jpa.config

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.sql.DataSource
import kotlin.collections.HashMap

@Configuration
@EnableJpaRepositories(
    basePackages = ["com.sean"],
    entityManagerFactoryRef = "entityManagerFactory",
    transactionManagerRef = "transactionManager"
)
class JpaConfig(
    private val env: Environment
) {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    fun factoryDataSource(): DataSource = DataSourceBuilder.create().build()

    @Primary
    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean =
        (LocalContainerEntityManagerFactoryBean()).apply {
            dataSource = factoryDataSource()
            setPackagesToScan("com.sean")
            jpaVendorAdapter = HibernateJpaVendorAdapter()
            jpaPropertyMap.putAll(properties())
        }

    @Primary
    @Bean
    fun transactionManager() = JpaTransactionManager(entityManagerFactory().`object`!!)

    private fun properties(): HashMap<String, Object> {
        var prop = HashMap<String, Object>()
        prop["spring.jpa.hibernate.ddl-auto"] =
            env.getProperty("spring.jpa.hibernate.ddl-auto") as Object
        prop["hibernate.dialect"] =
            "org.hibernate.dialect.MySQL5InnoDBDialect" as Object
        prop["hibernate.physical_naming_strategy"] =
            CamelCaseToUnderscoresNamingStrategy::class.java.name as Object
        prop["hibernate.implicit_naming_strategy"] =
            SpringImplicitNamingStrategy::class.java.name as Object
        prop["hibernate.id.new_generator_mappings"] =
            false as Object
        return prop
    }
}