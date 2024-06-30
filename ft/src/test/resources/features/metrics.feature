Feature: Checking metrics endpoints contains the expected metrics

  # Provided by StartupTimeMetricsListener
  Scenario: [startup metrics] When application is running, display start up metric content
    Given a private endpoint PRIVATE_METRICS is prepared
    When the request is sent
    Then the response status code should be 200
    And the body of the message contains the metrics:
      | application_ready_time_seconds   |
      | application_started_time_seconds |

  # Provided by SystemMetricsAutoConfiguration
  # UptimeMetrics, ProcessorMetrics, FileDescriptorMetrics, DiskSpaceMetricsBinder
  Scenario: [system metrics] When application is running, display system metric content
    Given a private endpoint PRIVATE_METRICS is prepared
    When the request is sent
    Then the response status code should be 200
    And the body of the message contains the metrics:
      | process_uptime_seconds     |
      | process_start_time_seconds |
      | system_cpu_count           |
      | system_load_average_1m     |
      | system_cpu_usage           |
      | process_cpu_usage          |
      | process_cpu_time_ns_total  |
      | process_files_open_files   |
      | process_files_max_files    |
      | disk_free_bytes            |
      | disk_total_bytes           |

  # Provided by JvmMetricsAutoConfiguration
  # JvmGcMetrics, JvmHeapPressureMetrics, JvmMemoryMetrics, JvmThreadMetrics, ClassLoaderMetrics, JvmInfoMetrics, JvmCompilationMetrics
  Scenario: [jvm metrics] When application is running, display jvm metric content
    Given a private endpoint PRIVATE_METRICS is prepared
    When the request is sent
    Then the response status code should be 200
    And the body of the message contains the metrics:
      | jvm_gc_max_data_size_bytes               |
      | jvm_gc_live_data_size_bytes              |
      | jvm_gc_memory_allocated_bytes_total      |
      | jvm_gc_memory_promoted_bytes_total       |
      | jvm_gc_concurrent_phase_time_seconds     |
      | jvm_gc_concurrent_phase_time_seconds_max |
#      | jvm_gc_pause_seconds                     | -- only available after the first GC run - so might not be available during testing
#      | jvm_gc_pause_seconds_max                 | -- only available after the first GC run - so might not be available during testing
      | jvm_memory_usage_after_gc                |
      | jvm_gc_overhead                          |
      | jvm_buffer_count_buffers                 |
      | jvm_buffer_memory_used_bytes             |
      | jvm_buffer_total_capacity_bytes          |
      | jvm_memory_used_bytes                    |
      | jvm_memory_committed_bytes               |
      | jvm_memory_max_bytes                     |
      | jvm_threads_peak_threads                 |
      | jvm_threads_daemon_threads               |
      | jvm_threads_live_threads                 |
      | jvm_threads_started_threads_total        |
      | jvm_threads_states_threads               |
      | jvm_classes_loaded_classes               |
      | jvm_classes_unloaded_classes_total       |
      | jvm_info                                 |
      | jvm_compilation_time_ms_total            |

  # Provided by ObservationAutoConfiguration
  Scenario: [requests metrics] When application is running, display requests metric content
    Given a private endpoint PRIVATE_METRICS is prepared
    When the request is sent
    Then the response status code should be 200
    And the body of the message contains the metrics:
      | http_server_requests_active_seconds     |
      | http_server_requests_active_seconds_max |
      | http_server_requests_seconds            |
      | http_server_requests_seconds_max        |
