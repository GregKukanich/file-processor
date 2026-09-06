resource "aws_sqs_queue" "file_processor_queue" {
  name                       = "file-processor-queue"
  delay_seconds              = 0
  max_message_size           = 1048576
  message_retention_seconds  = 345600
  receive_wait_time_seconds  = 0
  visibility_timeout_seconds = 30
}