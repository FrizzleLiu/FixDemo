#include <string.h>
#include <jni.h>
#include <stdio.h>
#include <fcntl.h>
#include <dlfcn.h>

#include <stdint.h>    /* C99 */

namespace art {
    namespace mirror {
        class Object {
        public:
            // The number of vtable entries in java.lang.Object.
            static constexpr size_t kVTableLength = 11;
            static uint32_t hash_code_seed;
            uint32_t klass_;

            uint32_t monitor_;
        };
        class Class: public Object {
        public:
            static constexpr uint32_t kClassWalkSuper = 0xC0000000;
            static constexpr size_t kImtSize = 0;	//IMT_SIZE;
            uint32_t class_loader_;
            uint32_t component_type_;
            uint32_t dex_cache_;
            uint32_t dex_cache_strings_;
            uint32_t iftable_;
            uint32_t name_;
            uint32_t super_class_;
            uint32_t vtable_;
            uint32_t access_flags_;
            uint64_t direct_methods_;
            uint64_t ifields_;
            uint64_t sfields_;
            uint64_t virtual_methods_;
            uint32_t class_size_;
            pid_t clinit_thread_id_;
            int32_t dex_class_def_idx_;
            int32_t dex_type_idx_;
            uint32_t num_direct_methods_;
            uint32_t num_instance_fields_;
            uint32_t num_reference_instance_fields_;
            uint32_t num_reference_static_fields_;
            uint32_t num_static_fields_;
            uint32_t num_virtual_methods_;
            uint32_t object_size_;
            uint32_t primitive_type_;
            uint32_t reference_instance_offsets_;
            uint32_t status_;
            static uint32_t java_lang_Class_;
        };

        class ArtField {
        public:
            uint32_t declaring_class_;
            uint32_t access_flags_;
            uint32_t field_dex_idx_;
            uint32_t offset_;
        };

        class ArtMethod {
        public:
            uint32_t declaring_class_;
            uint32_t dex_cache_resolved_methods_;
            uint32_t dex_cache_resolved_types_;
            uint32_t access_flags_;
            uint32_t dex_code_item_offset_;
            uint32_t dex_method_index_;
            uint32_t method_index_;
            struct PtrSizedFields {
                void* entry_point_from_interpreter_;
                void* entry_point_from_jni_;
                void* entry_point_from_quick_compiled_code_;
            } ptr_sized_fields_;
        };
    }
}