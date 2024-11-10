package com.example.benefit.domain.configuration.mongo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.mapping.MongoMappingContext

@Configuration
@EnableMongoAuditing
class MongoConfiguration {
    @Bean
    fun mongoTemplate(
        mongoDatabaseFactory: MongoDatabaseFactory,
        mongoMappingContext: MongoMappingContext,
    ): MongoTemplate {
        val dbRefResolver = DefaultDbRefResolver(mongoDatabaseFactory)
        val converter = MappingMongoConverter(dbRefResolver, mongoMappingContext)
        converter.setTypeMapper(DefaultMongoTypeMapper(null))
        return MongoTemplate(mongoDatabaseFactory, converter)
    }
}