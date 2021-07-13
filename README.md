# Spring-Boot SQS project
 - We are using standard queue and dead letter queue from AWS SQS
 - You need to create SQS standard queue 'sqs-demo-queue' and dead letter queue DLQ 'sqs-demo-queue' and bind this DLQ to standard queue 
so that after attempting max tries it will go to DLQ
 - You need to check application properties to set aws credential and endpoints
 - 'cloud.aws.stack.auto=false' , we set this property because while running application it will throw error.After keeping this property it will throw error
 - 'cloud.aws.end-point.uri' , when you create SQS standard queue you will get endpoint and set in properties file
