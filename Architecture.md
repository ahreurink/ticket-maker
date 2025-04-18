# Architecture

```mermaid
graph TD
    A[core-loop Module] -->|Depends on| B[LLM]
    A[core-loop Module] -->|Depends on| C[Requests]
    A[core-loop Module] -->|Depends on| D[Database]
    B[LLM] -->|Depends on| E[Config]
    C[Requests] -->|Depends on| E[Config]
    D[Database] -->|Depends on| E[Config]
```

### core-loop
- **Description**: The `core-loop` module implements the core looping functionality.
- **Structure**:
  - `src/main/java`: Contains the main Java source files.
  - `src/test/java`: Contains test classes for the module.

### llm
- **Description**: The `llm` module provides functionalities related to large language models.
- **Structure**:
  - `src/main/java`: Contains the main Java source files.
  - `src/test/java`: Contains test classes for the module.